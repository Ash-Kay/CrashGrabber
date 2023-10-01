PublishPlugin: https://github.com/gradle-nexus/publish-plugin/

export following props to global gradle.properties (Should not be included in git)
```txt
#Signing Details
signing.keyId=abc
signing.password=abc
signing.secretKeyRingFile=abc

#OSSRH Account Details
ossrh.username=abc
ossrh.password=abc
```

Global gradle.properties can be found at $GRADLE_HOME