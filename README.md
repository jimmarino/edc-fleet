# EDC Registry

This repository contains components implementing an EDC Registry:

- A registry server and tooling based on [xRegsitry](https://xregistry.io/).
- A reconciler for synchronizing EDC management domains with resource states specified in an hierarchy of registry servers. 

**This repository is currently experimental**
  
Build an run the xRegistry server:

```
./gradlew clean shadowJar
java -Dedc.participant.id="test" -jar registry/launcher/build/libs/registry-server.jar
```