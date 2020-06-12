package app.noah.service;

import app.noah.domain.PouchCategory;
import app.noah.dto.PouchCategoryRequestDto;
import app.noah.repository.pouch.PouchRepository;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PouchService
{
    private final PouchRepository pouchRepository;

    private final PouchCategoryRepository pouchCategoryRepository;

    @Transactional
    public Map<String,Object> insertPouchCategory(PouchCategoryRequestDto dto)
    {
        Map<String,Object> result = new HashMap<>();
        System.out.println(">> insert");
        PouchCategory category = new PouchCategory(dto.getPouchCategoryTitle(),dto.getSortKey());
        PouchCategory temp= pouchCategoryRepository.save(category);
        result.put("id", temp.getIdPouchCategory()+"_INSERT");
        return result;
    }

    @Transactional//write(오버라이딩)
    public Map<String,Object> updatePouchCategory(Long id, PouchCategoryRequestDto dto)
    {
        Map<String,Object> result = new HashMap<>();
        String status = null;
        PouchCategory temp = null;
        PouchCategory category = pouchCategoryRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 캐스트 카테고리가 없습니다.->"+id));

        category.update(dto.getPouchCategoryTitle(),dto.getSortKey(),dto.getIsDisplay());

        result.put("data",id);

        return result;
    }
}
