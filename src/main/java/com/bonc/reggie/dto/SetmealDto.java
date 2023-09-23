package com.bonc.reggie.dto;

import com.bonc.reggie.entity.Setmeal;
import com.bonc.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
