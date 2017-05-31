# FPM

https://github.com/jordansissel/fpm

Use the Gradle Plugin [packaging-rpm.gradle](packaging-rpm.gradle) to enable
support for building RPM packages.

`build.gradle` contents:

```gradle
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.kenshoo:gradle-fpm:0.5")
    }
}

war {
    archiveName = "ROOT.war"
    destinationDir = new File("${rootDir}/runway/FS_ROOT/opt/tomcat/webapps")
}

apply from: 'https://git.gogoair.com/common/gradle-config/raw/master/packaging-rpm.gradle'
```

This enables the `rpm` task.

```bash
gradle rpm
```

## RPM Dependency Packages
To add required dependent rpm packages you can use the following snippet:

```gradle
// must be *after* the "apply from: ..." statement
packaging.dependencies = ['dependency_package_name', 'gcc']
```

## Extra FPM Options

[packaging-rpm.gradle](packaging-rpm.gradle) has a good set of default options.
To add more options to the FPM command line, use the following snippet:

```gradle
// must be *after* the "apply from: ..." statement
packaging.extraOptions << [ '--new-option': "value" ]
```

To see all available FPM options, [install
FPM](https://fpm.readthedocs.io/en/latest/installing.html) and run `fpm --help`.

### Example: Add After Or Before Install Scripts

RPM packages support scripts that run before or after package installation. To
enable this feature, create a script and add the associated FPM option. The
typical use case will be running commands after installation.

```bash
vim runway/after-install.sh
chmod +x runway/after-install.sh
```

Contents of `runway/after-install.sh`:

```bash
#!/bin/bash
echo "I ran after RPM installation."
```

Added option to `build.gradle`:

```gradle
packaging.extraOptions << [ '--after-install': "${rootDir}/runway/after-install.sh" ]
```

To have a script run before installation, repeat the example steps, but replace
`after-install` with `before-install`.
