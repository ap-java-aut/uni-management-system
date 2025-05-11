package org.uni.framework;

import jakarta.persistence.EntityGraph;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ServiceBase<T extends UniEntity> {
    private Class<T> clazz;

    protected ServiceBase(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void persist(T entity) {
        getSessionFactory().inTransaction(session -> {
            session.persist(entity);
        });
    }

    public void remove(int id) {
        getSessionFactory().inTransaction(session -> {
            session.remove(session.getReference(clazz, id));
        });
    }

    public void remove(T entity) {
        getSessionFactory().inTransaction(session -> {
            session.remove(entity);
        });
    }

    public T fetchById(int id) {
        return getSessionFactory().fromTransaction(session -> session.get(clazz, id));
    }

    public T fetchById(int id, Function<Session, RootGraph<T>> rootGraphCreator) {
        return getSessionFactory().fromTransaction(session -> {
            RootGraph<T> graph = rootGraphCreator.apply(session);

            return session.byId(clazz)
                    .withLoadGraph(graph)
                    .load(id);
        });
    }

    public List<T> fetchAll() {
        return getSessionFactory().fromTransaction(session -> session.createSelectionQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList());
    }

    public List<T> fetchAll(Function<Session, RootGraph<T>> rootGraphCreator) {
        return getSessionFactory().fromTransaction(session -> {
            EntityGraph<T> graph = rootGraphCreator.apply(session);

            return session.createSelectionQuery("from " + clazz.getSimpleName(), clazz)
                    .setEntityGraph(graph, GraphSemantic.LOAD)
                    .getResultList();
        });
    }

    public T fetchRefById(int id) {
        return getSessionFactory().fromTransaction(session -> session.getReference(clazz, id));
    }

    protected SessionFactory getSessionFactory() {
        return SingletonSessionFactory.getSessionFactory();
    }
}
