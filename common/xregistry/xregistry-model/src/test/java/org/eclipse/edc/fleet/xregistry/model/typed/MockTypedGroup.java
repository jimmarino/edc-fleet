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

import org.eclipse.edc.fleet.xregistry.model.definition.GroupDefinition;

import java.util.Map;

public class MockTypedGroup extends TypedGroup {

    protected MockTypedGroup(Map<String, Object> untyped,
                             GroupDefinition definition,
                             TypeFactory typeFactory) {
        super(untyped,definition, typeFactory);
        this.definition = definition;
    }

    public Builder asBuilder() {
        return Builder.newInstance()
                .untyped(untyped)
                .definition(definition)
                .typeFactory(typeFactory);
    }

    public static class Builder extends AbstractType.Builder<GroupDefinition, Builder> {

        public static Builder newInstance() {
            return new Builder();
        }

        public TypedGroup build() {
            validate();
            return new MockTypedGroup(untyped, definition, typeFactory);
        }
    }

}
