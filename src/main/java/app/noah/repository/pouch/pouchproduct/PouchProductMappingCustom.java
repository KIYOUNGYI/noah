package app.noah.repository.pouch.pouchproduct;

import java.util.Map;

public interface PouchProductMappingCustom
{
    Map<String,Object> findByPouchId(Long idPouch);
    Map<String,Object> deletePouchProductByIdPouchAndIdProduct(Long idPouch,Long idProduct);
}
