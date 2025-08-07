package microservices.book.multiplication.application.ports.input;

import microservices.book.multiplication.application.ports.input.query.BaseQuery;

public interface IQueryHandler<Q extends BaseQuery<T>, T, R> {
    R handle(Q query);
}
