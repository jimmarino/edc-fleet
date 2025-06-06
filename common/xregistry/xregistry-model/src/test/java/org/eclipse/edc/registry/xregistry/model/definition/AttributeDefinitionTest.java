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

package org.eclipse.edc.registry.xregistry.model.definition;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AttributeDefinitionTest {

    @Test
    void verify_equals() {
        assertThat(AttributeDefinition.Builder.newInstance().name("foo").build())
                .isEqualTo(AttributeDefinition.Builder.newInstance().name("foo").build());
    }
}