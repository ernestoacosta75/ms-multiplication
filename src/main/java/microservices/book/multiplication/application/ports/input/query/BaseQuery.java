package microservices.book.multiplication.application.ports.input.query;

public abstract class BaseQuery<T> {
    private final T queryId;

    public BaseQuery() {
        this.queryId = null;
    }

    public BaseQuery(T queryId) {
        this.queryId = queryId;
    }

    public T getQueryId() {
        return queryId;
    }
}
