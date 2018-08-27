public abstract class BenchmarkResult {
    protected String solverName;
    protected BenchmarkResult(String solverName) {
        this.solverName = String.format("%-20s", solverName);
    }
}
