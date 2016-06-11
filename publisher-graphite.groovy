  configure { project ->
    project /  'publishers' << 'org.jenkinsci.plugins.graphiteIntegrator.GraphitePublisher' {
        selectedIP 'carbon.hostedgraphite.com'
        metrics {
            'org.jenkinsci.plugins.graphiteIntegrator.Metric' {
            queueName "${SRC_JOB}"
            delegate.name('BUILD_DURATION')
              }
            'org.jenkinsci.plugins.graphiteIntegrator.Metric' {
            queueName "${SRC_JOB}"
            delegate.name('BUILD_FAILED')
              }
            'org.jenkinsci.plugins.graphiteIntegrator.Metric' {
            queueName "${SRC_JOB}"
            delegate.name('BUILD_SUCCESSFUL')
              }
          }
      }
  }