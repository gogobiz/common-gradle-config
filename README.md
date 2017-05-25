# Gogo Gradle Configurations

* [packaging-rpm.gradle](fpm.md)

This is a repo for base gradle configurations to be inherited by multiple
projects. Import these configurations into your project like so:

```groovy
apply from: 'https://git.gogoair.com/common/gradle-config/raw/master/base-static-analysis.gradle'
```

Future goals:

* Upload to Gradle Plugin Repository, then make this Project Private
