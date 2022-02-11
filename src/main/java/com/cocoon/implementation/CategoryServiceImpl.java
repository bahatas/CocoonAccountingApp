package com.cocoon.implementation;

import com.cocoon.dto.CategoryDTO;
import com.cocoon.dto.CompanyDTO;
import com.cocoon.entity.Category;
import com.cocoon.entity.Company;
import com.cocoon.exception.CocoonException;
import com.cocoon.repository.CategoryRepo;
import com.cocoon.service.CategoryService;
import com.cocoon.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepo categoryRepo;
    private MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepo categoryRepo, MapperUtil mapperUtil) {
        this.categoryRepo = categoryRepo;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category -> mapperUtil.convert(category,new CategoryDTO())).collect(Collectors.toList());
    }

    @Override
    public void save(CategoryDTO categoryDTO) throws CocoonException {
        Category category = mapperUtil.convert(categoryDTO, new Category());
        if (categoryRepo.existsByDescription(category.getDescription()))
        throw new CocoonException("category is already exist");
        category.setEnabled(true);
        CompanyDTO companyDTO=new CompanyDTO();
        companyDTO.setId(8l);
        Company company = mapperUtil.convert(companyDTO, new Company());
        category.setCompany(company);//todo update with SecurityContext
        categoryRepo.save(category);
    }

    @Override
    public CategoryDTO getCategoryByDescription(String descrition) throws CocoonException {
        Category category = categoryRepo.getByDescription(descrition);
        if (category==null)
            throw new CocoonException("there is no category which you search");
        CategoryDTO categoryDTO = mapperUtil.convert(category, new CategoryDTO());
        return categoryDTO;
    }

    @Override
    public void update(CategoryDTO categoryDTO) throws CocoonException {
        Category category = categoryRepo.getById(categoryDTO.getId());
        if (category==null)
            throw new CocoonException("there is no cateory");
        category.setDescription(categoryDTO.getDescription());
        categoryRepo.save(category);


    }

    @Override
    public CategoryDTO getById(Long id) {
        Category category = categoryRepo.getById(id);
        CategoryDTO categoryDTO = mapperUtil.convert(category, new CategoryDTO());
        return categoryDTO;
    }


}
