package com.vaadin.training.grid.exercises.ex2;

import java.time.LocalDate;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.training.grid.exercises.MainLayout;

@Route(value = FilteringDataProvider.ROUTE, layout = MainLayout.class)
public class FilteringDataProvider extends Composite<VerticalLayout> {

    public static final String ROUTE = "ex2";
    public static final String TITLE = "Exercise 2";

    private final ListDataProvider<Product> dataProvider;

    public FilteringDataProvider() {
        final VerticalLayout layout = getContent();
        layout.setWidth("100%");

        dataProvider = DataProviderHelper.createProductDataProvider();

        // TODO create layout for DateFields
        final DatePicker fromField = new DatePicker("Start date");
        final DatePicker toField = new DatePicker("End date");
        final Button filter = new Button("Filter");
        final HorizontalLayout filters = new HorizontalLayout(fromField, toField, filter);
        filters.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        layout.add(filters);

        // String data = dataProvider.getItems().stream()
        //         .map(Product::getName)
        //         .collect(Collectors.joining(", "));
		// System.out.println(data);
        // TODO create and populate Grid
        Grid<Product> grid = new Grid<>(Product.class);
        grid.setColumns("name", "price", "available");
		grid.setItems(dataProvider);
        layout.add(grid);

    }

    private boolean filterProduct(Product product, LocalDate start, LocalDate end) {
        // TODO implement filtering logic here.
		if (product.getAvailable() == null) {
			if( start != null || end != null){
				return false; //null data
			}
		}
		if(start == null && end == null){
			return true;
		}else if(start==null){
			return product.getAvailable().isBefore(end) || product.getAvailable().equals(end);
		}else if(end==null){
			return product.getAvailable().isAfter(start) || product.getAvailable().equals(start);
		}else{
			boolean atEnds = product.getAvailable().equals(start) || product.equals(end);
			boolean inBetween = product.getAvailable().isAfter(start) && product.getAvailable().isBefore(end);

			return atEnds || inBetween;
		}
    }

}
