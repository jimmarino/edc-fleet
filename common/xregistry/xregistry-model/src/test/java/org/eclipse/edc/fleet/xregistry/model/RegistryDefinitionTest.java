package org.eclipse.edc.fleet.xregistry.model;

import org.junit.jupiter.api.Test;

/**
 *
 */
class RegistryDefinitionTest {

    @Test
    void verify()  {
        var group = GroupDefinition.Builder.newInstance()
                .singular("schema")
                .plural("schemas")
                .build();

        var registry = RegistryDefinition.Builder.newInstance()
                .group(group)
                .build();

        System.out.println();

    }
}