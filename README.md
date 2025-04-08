# EDC Fleet Coordination 

This repository contains components implementing Fleet Coordination:

- A registry server and tooling based on [xRegsitry](https://xregistry.io/)                                                .
- A reconciler for synchronizing EDC management domains with resource states specified in an hierarchy of registry servers. 
- An eventing system based on [CloudEvents](https://cloudevents.io/)

**This repository is currently experimental**
  
Build an run the xRegistry server:

```
./gradlew clean shadowJar
java -Dedc.participant.id="test" -jar registry/launcher/build/libs/registry-server.jar
```