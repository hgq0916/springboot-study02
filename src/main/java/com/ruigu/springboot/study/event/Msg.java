package com.ruigu.springboot.study.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg<T> implements ResolvableTypeProvider {

    T data;

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(data));
    }

}
