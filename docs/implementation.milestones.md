# Implementation Milestones

## 1. xRegistry Library

- Provides the core library for building xRegistries including an extensible data model parser and validator.

## 2. xRegistry Server

- An EDC-based xRegistry server that implements the core xRegistry specification and provides support for policy
  artifacts.

## 3. Continuous Integration Tooling

- Gradle-based tooling to enable publishing of policy artifacts directly from a Git repository to an xRegistry server as
  part of a Continuous Integration pipeline.

## 4. Reconciler Component

- An EDC component that supports deployment of poliocy artifacts to an EDC-based cluser. 

## 5. CloudEvents EDC Extension

- An extension which publishes EDC events as CloudEvents

## 6. CloudEvents Provider Integrations

- EDC extensions that publish CloudEvents to the following messaging systems:
  - Kafka (Open Source)
  - AWS EventBridge
  - Azure Event Grid

## 7. Tractus-X xRegistry Distribution

- A Kubernetes-based XRegistry server distribution that includes the following Catena-X extensions:
  - Support for validation of Catena-X specific policies
  - Pre-defined Catena-X policy and credential schemas

## 8. Tractus-X Reconciler Distribution

- A Kubernetes-based distribution of the EDC Reconciler component. 

## 9. Tractus-X Events Example

- An example application that uses EDC CloudEvents.

## 10. Standardization Track

- Submit a **Policy Registry** proposal to the EDWG as an official Eclipse Specification Project.
