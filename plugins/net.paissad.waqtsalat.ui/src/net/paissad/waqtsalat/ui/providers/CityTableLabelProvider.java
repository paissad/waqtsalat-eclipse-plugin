package net.paissad.waqtsalat.ui.providers;

import net.paissad.waqtsalat.locationsprovider.api.City;
import net.paissad.waqtsalat.locationsprovider.api.Country;
import net.paissad.waqtsalat.ui.util.WaqtSalatUIHelper;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class CityTableLabelProvider implements IBaseLabelProvider, ITableLabelProvider {

    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
                                                                                  new ComposedAdapterFactory(
                                                                                          ComposedAdapterFactory.Descriptor.Registry.INSTANCE));

    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return true;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    }

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        if ((element instanceof City) && columnIndex == 0) {
            Country country = ((City) element).getCountry();
            String code = (country == null) ? null : country.getCode();
            return WaqtSalatUIHelper.getFlagForCountryCode(code);
        }
        return adapterFactoryLabelProvider.getColumnImage(element, columnIndex);
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        String result = "";
        if (element instanceof City) {
            City city = (City) element;
            switch (columnIndex) {
                case 0:
                    result = city.getCountry().getName();
                    break;
                case 1:
                    result = city.getName();
                    break;
                case 2:
                    result = city.getRegion();
                    break;
                case 3:
                    result = city.getPostalCode();
                    break;
                default:
                    break;
            }
        } else {
            result = adapterFactoryLabelProvider.getColumnText(element, columnIndex);
        }
        return result;
    }
}
