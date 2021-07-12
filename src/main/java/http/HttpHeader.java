package http;

import util.MultiValueMap;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpHeader implements MultiValueMap<String, String> , Serializable {



    public String getFirst(String key) {
        return null;
    }


    public void add(String key, String value) {

    }


    public void addAll(String key, List<? extends String> values) {

    }


    public void addAll(MultiValueMap<String, String> values) {

    }


    public void set(String key, String value) {

    }


    public void setAll(Map<String, String> values) {

    }


    public Map<String, String> toSingleValueMap() {
        return null;
    }


    public int size() {
        return 0;
    }


    public boolean isEmpty() {
        return false;
    }


    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public List<String> get(Object key) {
        return null;
    }

    @Override
    public List<String> put(String key, List<String> value) {
        return null;
    }

    @Override
    public List<String> remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends List<String>> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<List<String>> values() {
        return null;
    }

    @Override
    public Set<Entry<String, List<String>>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
