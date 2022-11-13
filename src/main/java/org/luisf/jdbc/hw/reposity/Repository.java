package org.luisf.jdbc.hw.reposity;

import java.util.List;

public interface Repository <T> {
    List<T> list();

    T searchId(Long id);

    void store(T t);

    void delete(Long id);
}
