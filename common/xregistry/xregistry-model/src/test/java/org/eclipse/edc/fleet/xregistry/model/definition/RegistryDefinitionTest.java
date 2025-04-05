package org.eclipse.edc.fleet.xregistry.model.definition;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
class RegistryDefinitionTest {

    @Test
    void verify() {
        var group = GroupDefinition.Builder.newInstance()
                .singular("schemagroup")
                .plural("schemasgroup")
                .build();

        var registry = RegistryDefinition.Builder.newInstance()
                .group(group)
                .build();

        assertThat(registry.getGroups()).containsKey("schemasgroup");
    }
}