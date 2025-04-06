/*
 *  Copyright (c) 2025 Metaform Systems, Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems, Inc. - initial API and implementation
 *
 */

package org.eclipse.edc.fleet.registry.server.defaults;

import org.eclipse.edc.fleet.xregistry.model.definition.RegistryDefinition;
import org.eclipse.edc.fleet.xregistry.model.typed.TypeFactory;
import org.eclipse.edc.fleet.xregistry.model.typed.TypeFactoryImpl;
import org.eclipse.edc.fleet.xregistry.model.typed.TypedRegistry;
import org.eclipse.edc.fleet.xregistry.model.typed.TypedResource;
import org.eclipse.edc.spi.result.ServiceResult;
import org.jetbrains.annotations.NotNull;
import rg.eclipse.edc.fleet.registry.server.spi.resource.ResourceTypeStore;
import rg.eclipse.edc.fleet.registry.server.spi.store.RegistryStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation.
 */
public class DefaultRegistryStore implements RegistryStore {
    private final TypeFactory typeFactory;
    private RegistryDefinition registryDefinition;
    private Map<Class<?>, ResourceTypeStore<?>> cache = new HashMap<>();

    public DefaultRegistryStore() {
        typeFactory = new TypeFactoryImpl();
        registryDefinition = RegistryDefinition.Builder.newInstance().build();
    }

    @Override
    public void register(ResourceTypeStore<?> store) {
        cache.put(store.getType(), store);
    }

    @Override
    public @NotNull Map<String, Object> fetch(int offset, int maxResults) {
        var registry = TypedRegistry.Builder.newInstance()
                .definition(registryDefinition)
                .untyped(new HashMap<>())
                .typeFactory(typeFactory);

        cache.values().stream()
                .flatMap(store -> store.fetchGroups(offset, maxResults).stream())
                .forEach(registry::group);
        return registry.build().asMap();
    }

    @Override
    public ServiceResult<Void> createResource(TypedResource<?> resource) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServiceResult<Void> updateResource(TypedResource<?> resource) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ServiceResult<Void> deleteResource(String id) {
        throw new UnsupportedOperationException();
    }
}
