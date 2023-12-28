package controller;

import entity.*;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import services.MachineFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.Axis;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.criteria.CriteriaQuery;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

@ManagedBean(name = "machineController")
@SessionScoped
public class MachineController implements Serializable {

    @EJB
    private services.MachineFacade ejbFacade;
    private List<Machine> items = null;
    private Machine selected;
    private Employe selectedEmploye;

    public Employe getSelectedEmploye() {
        return selectedEmploye;
    }

    public void setSelectedEmploye(Employe selectedEmploye) {
        this.selectedEmploye = selectedEmploye;
    }

    public MachineController() {
    }

    public Machine getSelected() {
        return selected;
    }

    public void setSelected(Machine selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MachineFacade getFacade() {
        return ejbFacade;
    }

    public Machine prepareCreate() {
        selected = new Machine();
        initializeEmbeddableKey();
        selectedEmploye = null;
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MachineCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MachineUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MachineDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Machine> getItems() {
      
        if (selectedEmploye != null) {
            items = getFacade().findByEmploye(selectedEmploye);
        } else {
            items = getFacade().findAll();
        }
    
    return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Machine> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Machine> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    public List<Employe> getEmployeList() {
    return getFacade().findAllEmployes();
    }
    
    public List<Object[]> getMachinesByYear() {
    return getFacade().findMachinesByYear();
    }
    
    
    
    public LineChartModel getMachineChartModel(String titre,String anne,String nb) {
    LineChartModel model = new LineChartModel();
    ChartSeries machinesByYear = new ChartSeries();

    
    List<Object[]> machinesData = getMachinesByYear();

    for (Object[] data : machinesData) {
        int year = (int) data[0];
        long machineCount = (long) data[1];
        machinesByYear.set(year, machineCount);
    }
    

    model.addSeries(machinesByYear);
    
    model.setTitle(titre);
    model.setLegendPosition("se");
    model.setZoom(true);
    

        org.primefaces.model.chart.Axis xAxis = model.getAxis(AxisType.X);
    xAxis.setLabel(anne);

        org.primefaces.model.chart.Axis yAxis = model.getAxis(AxisType.Y);
    yAxis.setLabel(nb);

    return model;
}
    


    @FacesConverter(forClass = Machine.class)
    public static class MachineControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MachineController controller = (MachineController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "machineController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Machine) {
                Machine o = (Machine) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Machine.class.getName()});
                return null;
            }
        }
       
       

    }

}
