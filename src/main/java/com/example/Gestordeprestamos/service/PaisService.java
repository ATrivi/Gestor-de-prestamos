package com.example.Gestordeprestamos.service;
import com.example.Gestordeprestamos.external.CountryInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaisService {

    private final RestTemplate restTemplate = new RestTemplate();

    public CountryInfo getInfoPorNombre(String nombrePais) {

        String url = "https://restcountries.com/v3.1/name/" + nombrePais;
        CountryInfo[] respuesta = restTemplate.getForObject(url, CountryInfo[].class);

        if (respuesta != null && respuesta.length > 0) {
            return respuesta[0]; // devolvemos el primer resultado
        }

        return null; // país no encontrado
    }
}
