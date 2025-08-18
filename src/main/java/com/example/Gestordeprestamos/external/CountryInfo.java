package com.example.Gestordeprestamos.external;
import java.util.List;
import java.util.Map;


//  Esta clase no necesita getters/setters porque solo la vamos a usar para deserializar (convertir JSON a objeto).

public class CountryInfo {

    public Name name;
    public List<String> capital;
    public Map<String, Currency> currencies;
    public Flags flags;

    public static class Name {
        public String common;
    }

    public static class Currency {
        public String name;
    }

    public static class Flags {
        public String png;
    }
}
