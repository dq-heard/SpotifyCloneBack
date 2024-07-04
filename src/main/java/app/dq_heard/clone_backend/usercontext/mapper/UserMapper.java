package app.dq_heard.clone_backend.usercontext.mapper;

import app.dq_heard.clone_backend.usercontext.ReadUserDTO;
import app.dq_heard.clone_backend.usercontext.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  ReadUserDTO readUserDTOToUser(User entity);

}
