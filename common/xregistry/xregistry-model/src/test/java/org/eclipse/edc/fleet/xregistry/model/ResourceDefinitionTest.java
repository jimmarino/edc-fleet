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

package org.eclipse.edc.fleet.xregistry.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResourceDefinitionTest {

    @Test
    void verifyAttributeOverlap() {
        var attributeDefinition = AttributeDefinition.Builder.newInstance().name("foo").build();
        assertThatThrownBy(() -> ResourceDefinition.Builder.newInstance()
                .singular("bar")
                .plural("bars")
                .attribute(attributeDefinition)
                .metaAttribute(attributeDefinition)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }
}