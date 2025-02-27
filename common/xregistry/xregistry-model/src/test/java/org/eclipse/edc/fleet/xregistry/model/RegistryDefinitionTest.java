package org.eclipse.edc.fleet.xregistry.model;

import org.junit.jupiter.api.Test;

/**
 *
 */
class RegistryDefinitionTest {

    @Test
    void verify()  {
        var group = GroupDefinition.Builder.newInstance()
                .singular("schemagroup")
                .plural("schemasgroup")
                .build();

        var registry = RegistryDefinition.Builder.newInstance()
                .group(group)
                .build();

        System.out.println();

    }
}