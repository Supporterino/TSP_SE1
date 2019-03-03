package statistics;

public enum ConfigurationStatistics {
    instance;

    static final String median = "median";
    static final String mean = "mean";
    static final String mode = "mode";
    static final String quantile = "quantile";
    static final String range = "range";
    static final String iqr = "iqr";
    static final String sd = "sd";
    final String firstArgument = "d";
    final String splitSymbol = "-";
    final String scenarioSeparator = ",";
    final String allScenarios = "all";
    final String equalSymbol = "=";
    final String wrongStatisticMode = "Statistic mode no found: ";
    char scenarioPrefix = 's';
}
