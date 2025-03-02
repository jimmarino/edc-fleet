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

package org.eclipse.edc.fleet.xregistry.model.typed;

import org.eclipse.edc.fleet.xregistry.model.definition.ResourceDefinition;

import java.util.Map;

public class MockTypedResource extends TypedResource {

    public MockTypedResource(Map<String, Object> untyped, ResourceDefinition definition, TypeFactory typeFactory) {
        super(untyped, definition, typeFactory);
    }

    public Builder asBuilder() {
        return Builder.newInstance()
                .untyped(untyped)
                .definition(definition)
                .typeFactory(typeFactory);
    }

    public static class Builder extends AbstractType.Builder<ResourceDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public TypedResource build() {
            validate();
            return new MockTypedResource(untyped, definition, typeFactory);
        }


    }

}
