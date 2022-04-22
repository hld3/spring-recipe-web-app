package com.dodson.spring_web_recipe.services;

import java.util.Set;

import com.dodson.spring_web_recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllMeasurements();
}
