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

package org.eclipse.edc.fleet.xregistry.library.validation;

import org.eclipse.edc.fleet.xregistry.model.AbstractTypeDefinition;
import org.eclipse.edc.fleet.xregistry.model.GroupDefinition;

import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.eclipse.edc.fleet.xregistry.library.validation.ValidationResult.invalidType;
import static org.eclipse.edc.fleet.xregistry.library.validation.ValidationResult.success;

/**
 * Validates a group entry.
 */
public class GroupValidator implements RegistryTypeValidator<GroupDefinition> {
    private RegistryTypeValidator<AbstractTypeDefinition> attributeValidator;

    public GroupValidator(RegistryTypeValidator<AbstractTypeDefinition> attributeValidator) {
        this.attributeValidator = requireNonNull(attributeValidator, "attributeValidator");
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ValidationResult validate(Map<String, Object> groupContainer, GroupDefinition groupDefinition) {
        return groupContainer.entrySet().stream().map(groupEntry -> {
            if (!(groupEntry.getValue() instanceof Map groupMap)) {
                // groups must be a map
                return invalidType(format("%s[%s]", groupDefinition.getContext(), groupEntry.getKey()));
            }
            var result = attributeValidator.validate(groupMap, groupDefinition);
            return groupDefinition.getResources().values().stream().map(resourceDefinition -> {
                var resources = groupMap.get(resourceDefinition.getPlural());
                if (resources == null) {
                    return success();
                }
                if (!(resources instanceof Map resourceMap)) {
                    var context = format("%s[%s].%s", groupDefinition.getContext(), groupEntry.getKey(), resourceDefinition.getPlural());
                    return invalidType(context);
                }
                Set<Map.Entry> set = resourceMap.entrySet();
                return set.stream().map(resourceEntry -> {
                    if (!(resourceEntry.getValue() instanceof Map resourceEntryMap)) {
                        var context = format("%s[%s].%s[%s]", groupDefinition.getContext(), groupEntry.getKey(), resourceDefinition.getPlural(), resourceEntry.getKey());
                        return invalidType(context);
                    }
                    return attributeValidator.validate(resourceEntryMap, resourceDefinition);
                }).reduce(success(), ValidationResult::coalesce);
            }).reduce(result, ValidationResult::coalesce);
        }).reduce(success(), ValidationResult::coalesce);
    }
}