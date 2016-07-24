package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.Type;

/**
 * Created by van on 23/07/2016.
 */
public interface Typed<T extends Type> {

    T getType();
}
