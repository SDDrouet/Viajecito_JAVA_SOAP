import axios from 'axios';
import { XMLParser } from 'fast-xml-parser';

const SOAP_URL = 'http://10.69.99.199:8080/aero_condor_java_soap/AeroCondorController';
const parser = new XMLParser({ 
  ignoreAttributes: false,
  removeNSPrefix: true // Remueve prefijos para facilitar el parsing
});

// Función para manejar diferentes estructuras de respuesta SOAP
const parseSOAPResponse = (xmlData, responseName) => {
  const json = parser.parse(xmlData);
  
  // Buscar la respuesta en diferentes posibles ubicaciones
  let response = null;
  
  // Con removeNSPrefix: true
  if (json?.Envelope?.Body?.[responseName]) {
    response = json.Envelope.Body[responseName];
  }
  // Sin removeNSPrefix o con estructura S:Envelope
  else if (json?.['S:Envelope']?.['S:Body']) {
    const body = json['S:Envelope']['S:Body'];
    for (let key in body) {
      if (key.includes(responseName.replace('Response', ''))) {
        response = body[key];
        break;
      }
    }
  }

  return response?.return || response?.['return'] || null;
};

// 🔹 1. Obtener boletos por usuario
export const obtenerBoletosPorUsuario = async (idUsuario) => {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:con="http://controller.aero_condor_java_soap.edu.ec/">
    <soapenv:Header/>
    <soapenv:Body>
      <con:obtenerBoletosPorUsuario>
        <arg0>${idUsuario}</arg0>
      </con:obtenerBoletosPorUsuario>
    </soapenv:Body>
  </soapenv:Envelope>`;

  try {
    console.log('📤 Enviando request para obtener boletos del usuario:', idUsuario);
    
    const { data } = await axios.post(SOAP_URL, xml, {
      headers: { 'Content-Type': 'text/xml;charset=UTF-8' }
    });

    console.log('📥 Respuesta SOAP recibida');

    const boletos = parseSOAPResponse(data, 'obtenerBoletosPorUsuarioResponse');
    
    if (!boletos) {
      console.log('No se encontraron boletos');
      return [];
    }

    // Asegurar que sea un array
    const listaBoletos = Array.isArray(boletos) ? boletos : [boletos];
    
    console.log(`Se encontraron ${listaBoletos.length} boletos`);

    return listaBoletos.map(b => ({
      idBoleto: parseInt(b.idBoleto),
      numeroBoleto: b.numeroBoleto,
      fechaCompra: b.fechaCompra,
      precio: parseFloat(b.precioCompra),
      vuelo: {
        id: parseInt(b.idVuelo?.idVuelo),
        codigo: b.idVuelo?.codigoVuelo,
        origen: b.idVuelo?.idCiudadOrigen?.codigoCiudad,
        destino: b.idVuelo?.idCiudadDestino?.codigoCiudad,
        horaSalida: b.idVuelo?.horaSalida,
        ciudadOrigenNombre: b.idVuelo?.idCiudadOrigen?.nombreCiudad,
        ciudadDestinoNombre: b.idVuelo?.idCiudadDestino?.nombreCiudad
      }
    }));
  } catch (err) {
    console.error('❌ Error al obtener boletos:', err);
    console.error('Detalles:', err.response?.data || err.message);
    return [];
  }
};

// 🔹 2. Registrar compra de boletos
export const registrarBoletos = async ({ idUsuario, vuelos, esCredito = false, numeroCuotas = 0, tasaInteresAnual = 0 }) => {
  const vuelosXML = vuelos.map(v => `
        <vuelos>
          <idVuelo>${v.idVuelo}</idVuelo>
          <cantidad>${v.cantidad}</cantidad>
        </vuelos>`).join('');

  const xml = `<?xml version="1.0" encoding="UTF-8"?>
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:con="http://controller.aero_condor_java_soap.edu.ec/">
    <soapenv:Header/>
    <soapenv:Body>
      <con:comprar>
        <arg0>
          <idUsuario>${idUsuario}</idUsuario>
          <esCredito>${esCredito}</esCredito>
          <numeroCuotas>${numeroCuotas}</numeroCuotas>
          <tasaInteresAnual>${tasaInteresAnual}</tasaInteresAnual>
          ${vuelosXML}
        </arg0>
      </con:comprar>
    </soapenv:Body>
  </soapenv:Envelope>`;

  try {
    console.log('📤 Enviando request para registrar boletos');
    
    const { data } = await axios.post(SOAP_URL, xml, {
      headers: { 'Content-Type': 'text/xml;charset=UTF-8' }
    });

    const result = parseSOAPResponse(data, 'comprarResponse');
    
    console.log('Resultado de la compra:', result);
    
    return result === 'true' || result === true;
  } catch (err) {
    console.error('❌ Error al registrar boletos:', err);
    console.error('Detalles:', err.response?.data || err.message);
    return false;
  }
};

// 🔹 3. Obtener amortización por factura
export const obtenerAmortizacionPorFactura = async (idFactura) => {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:con="http://controller.aero_condor_java_soap.edu.ec/">
    <soapenv:Header/>
    <soapenv:Body>
      <con:obtenerAmortizacionPorFactura>
        <arg0>${idFactura}</arg0>
      </con:obtenerAmortizacionPorFactura>
    </soapenv:Body>
  </soapenv:Envelope>`;

  try {
    console.log('📤 Enviando request para obtener amortización de factura:', idFactura);
    
    const { data } = await axios.post(SOAP_URL, xml, {
      headers: { 'Content-Type': 'text/xml;charset=UTF-8' }
    });

    const amortizaciones = parseSOAPResponse(data, 'obtenerAmortizacionPorFacturaResponse');
    
    if (!amortizaciones) {
      console.log('No se encontraron amortizaciones');
      return [];
    }

    // Asegurar que sea un array
    const listaAmortizaciones = Array.isArray(amortizaciones) ? amortizaciones : [amortizaciones];
    
    console.log(`Se encontraron ${listaAmortizaciones.length} amortizaciones`);

    return listaAmortizaciones.map(a => ({
      idAmortizacion: parseInt(a.idAmortizacion),
      numeroCuota: parseInt(a.numeroCuota),
      valorCuota: parseFloat(a.valorCuota),
      interesPagado: parseFloat(a.interesPagado),
      capitalPagado: parseFloat(a.capitalPagado),
      saldo: parseFloat(a.saldo)
    }));
  } catch (err) {
    console.error('❌ Error al obtener amortización:', err);
    console.error('Detalles:', err.response?.data || err.message);
    return [];
  }
};