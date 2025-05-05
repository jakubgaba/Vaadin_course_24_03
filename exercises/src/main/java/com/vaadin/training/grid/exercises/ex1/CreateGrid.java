package com.vaadin.training.grid.exercises.ex1;

import java.io.Serial;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.training.grid.exercises.MainLayout;

@Route(value = CreateGrid.ROUTE, layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class CreateGrid extends VerticalLayout {
	@Serial
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "Exercise 1";
	public static final String ROUTE = "ex1";

	final PersonService service = new PersonService();

	public CreateGrid() {
		setWidth("100%");

		final List<Person> persons = service.getAllPersons();

		// TODO create a Grid
		final Grid<Person> grid = new Grid<>();

		add(grid);

		grid.setItems(persons);
		grid.addColumn(Person::getAge).setHeader("Age");
		grid.addColumn(Person::getName).setHeader( "Name");
		grid.addColumn(Person::getEmail).setHeader("Email");
		grid.addColumn(Person::getBirthday).setHeader("Birthday");
		
		// TODO create a DataProvider for the Grid
		
	}

}
