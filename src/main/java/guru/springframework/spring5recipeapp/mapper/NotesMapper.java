package guru.springframework.spring5recipeapp.mapper;

import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.dto.NotesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotesMapper {
    NotesMapper INSTANCE = Mappers.getMapper(NotesMapper.class);

    Notes NotesDTOToNotes(NotesDTO NotesDTO);

    NotesDTO NotesToNotesDTO(Notes Notes);
}
