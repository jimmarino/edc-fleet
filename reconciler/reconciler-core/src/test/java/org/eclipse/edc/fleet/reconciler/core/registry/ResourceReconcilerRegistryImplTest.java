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

package org.eclipse.edc.fleet.reconciler.core.registry;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.edc.fleet.spi.reconciler.ResourceReconciler;
import org.eclipse.edc.fleet.xregistry.model.typed.TypedResource;
import org.eclipse.edc.spi.result.ServiceResult;
import org.junit.jupiter.api.Test;

class ResourceReconcilerRegistryImplTest {

    @Test
    void verify_sort() {
        var registry = new ResourceReconcilerRegistryImpl();

        registry.registerRecociler(new MockReconciler("bar", emptyList()));
        registry.registerRecociler(new MockReconciler("baz", List.of("foo")));
        registry.registerRecociler(new MockReconciler("foo", List.of("bar")));

        var reconcilers = registry.getReconcilers();
        assertThat(reconcilers.get(0).resourceType()).isEqualTo("bar");
        assertThat(reconcilers.get(1).resourceType()).isEqualTo("foo");
        assertThat(reconcilers.get(2).resourceType()).isEqualTo("baz");

    }


    private static class MockReconciler implements ResourceReconciler<TypedResource<?>> {
        private String type;
        private final List<String> dependencies;

        public MockReconciler(String type, List<String> dependencies) {
            this.type = type;
            this.dependencies = dependencies;
        }

        @Override
        public List<String> dependentResources() {
            return dependencies;
        }

        @Override
        public String resourceType() {
            return type;
        }

        @Override
        public ServiceResult<Void> reconcile(Stream<TypedResource<?>> reasources) {
            throw new UnsupportedOperationException();
        }
    }
}