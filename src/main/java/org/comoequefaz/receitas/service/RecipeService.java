package org.comoequefaz.receitas.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.comoequefaz.receitas.dto.request.RecipeCreateRequest;
import org.comoequefaz.receitas.dto.request.RecipeUpdateRequest;
import org.comoequefaz.receitas.dto.response.RecipeResponse;
import org.comoequefaz.receitas.entity.Recipe;
import org.comoequefaz.receitas.exception.BusinessException;
import org.comoequefaz.receitas.exception.EntityNotFoundException;
import org.comoequefaz.receitas.mapper.RecipeMapper;
import org.comoequefaz.receitas.repository.RecipeRepository;

import java.util.List;

@ApplicationScoped
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeService(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Transactional
    public RecipeResponse create(RecipeCreateRequest request) {
        String normalizedTitle = request.title().trim();

        if (recipeRepository.existsByTitle(normalizedTitle)) {
            throw new BusinessException(
                    "Já existe uma receita cadastrada com este título."
            );
        }
        Recipe recipe = recipeMapper.toEntity(request);
        recipe.setTitle(normalizedTitle);

        if(recipe.getFavorite() == null) {
            recipe.setFavorite(false);
        }

        recipeRepository.persist(recipe);

        return recipeMapper.toResponse(recipe);
    }
    public List<RecipeResponse> listAll(){
        return recipeRepository.listAll()
                .stream()
                .map(recipeMapper::toResponse)
                .toList();
    }
    public RecipeResponse findById(Long id){
        Recipe recipe =  recipeRepository.findById(id);

        return recipeMapper.toResponse(recipe);
    }
    public List<RecipeResponse> searchByTitle(String title){
        if(title == null || title.isBlank() ){
            return listAll();
        }
        return recipeRepository.findByTitle(title)
                .stream()
                .map(recipeMapper::toResponse)
                .toList();
    }

    @Transactional
    public RecipeResponse update(
            Long id,
            RecipeUpdateRequest request) {

        Recipe recipe = findEntityById(id);
        String normalizedTitle = request.title().trim();

        if(recipeRepository.existsByTitleAndIdNot(normalizedTitle, id)){
            throw new BusinessException("Já existe uma receita com o título informado.");
        }
        recipeMapper.updateEntityFromRequest(request, recipe);
        recipe.setTitle(normalizedTitle);
        return recipeMapper.toResponse(recipe);

    }
    @Transactional
    public void delete(Long id){
        Recipe recipe = findEntityById(id);

        recipeRepository.delete(recipe);
    }

    private Recipe findEntityById(Long id) {
        return recipeRepository.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada para o id: " + id ));
    }
}
