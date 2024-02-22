package e.commerce.productservice.mapper.base;

public interface Mapper<Entity, Response> {

    Response toResponse(Entity entity);
}
