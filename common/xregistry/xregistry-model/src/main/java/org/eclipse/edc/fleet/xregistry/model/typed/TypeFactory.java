package org.eclipse.edc.fleet.xregistry.model.typed;

import org.eclipse.edc.fleet.xregistry.model.definition.AbstractTypeDefinition;

import java.util.Map;

/**
 * Delegates to {@link TypeFactory.Instantiator}s to create typed views of XRegistry data.
 */
public interface TypeFactory {

    /**
     * Instantiates a typed view for the XRegistry data.
     */
    @FunctionalInterface
    interface Instantiator<T extends AbstractTypeDefinition> {
        AbstractType<?> instantiate(Map<String, Object> untyped, T definition, TypeFactory typeFactory);
    }

    /**
     * Instantiates an XRegistry type.
     *
     * @param type       the type to instantiate
     * @param untyped    the untyped data
     * @param definition the type definition
     * @return the instance
     */
    <T extends AbstractType<D>, D extends AbstractTypeDefinition> T instantiate(Class<T> type, Map<String, Object> untyped, D definition);

    /**
     * Registers an {@link Instantiator}.
     */
    <T extends AbstractType<D>, D extends AbstractTypeDefinition> void register(Class<T> type, String name, Instantiator<D> instantiator);

}
