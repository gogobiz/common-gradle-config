# Gogo Gradle Configurations

* [packaging-rpm.gradle](fpm.md)

This is a repo for base gradle configurations to be inherited by multiple
projects. Import these configurations into your project like so:

```groovy
apply from: 'https://git.gogoair.com/common/gradle-config/raw/master/base-static-analysis.gradle'
```

Future goals:

* Upload to Gradle Plugin Repository, then make this Project Private


# FindBugs and PMD Rule/Path-based Exclusions

- To provide a FindBugs Filter file to be used as issue exclusion criteria, define the Gradle extra property *fbExcludeFilter*, specifying the path to the exclusion filter. Such a file can serve to exclude specific packages/classes/etc. from FindBugs scanning, **and/or** specific FindBugs bug types/codes/categories. For more info file format, see the [Filter Files chapter of the FindBugs manual](http://findbugs.sourceforge.net/manual/filter.html).
- To provide a PMD Ruleset file to be used in place of the default ruleset config, declare the Gradle extra property *pmdRuleset*, specifying the path to the ruleset file. The specified file will **replace completely** the default ruleset to use with PMD. As such, it is advisable to include all desired (e.g. default) rule categories and exclude only the rules you'd like not to scan for.
    - An additional property *pmdToolVersion* can also be set, to specify the PMD version to use. If left unspecified, a default version is used. NOTE that the ruleset file may need to look significantly different for newer PMD versions (e.g. > 6.0) than the one for older PMD versions, or the default one. The [sample file provided](samples/pmd_ruleset.xml) is intended to be template for the default PMD tool version.
- To exclude one or more paths completely from PMD scanning, define the Gradle extra property *pmdExcludePaths*, specifying either a **single** or a **list** of Ant-style path patterns.

Example:
```groovy
ext.fbExcludeFilter = "findbugs_excludes.xml"
ext.pmdRuleset = "pmd_ruleset.xml"
ext.pmdExcludePaths = "**/gogoair/customer/core/config/**"
apply from: '../gradle-config/local-base-static-analysis.gradle'
```

See the [samples](samples) directory for example filter/ruleset files.
