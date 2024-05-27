package it.epicode.DevicesManagment.businesslayer.services.impl;

import it.epicode.DevicesManagment.businesslayer.services.interfaces.DeviceService;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.AlreadyAssignedException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.datalayer.entities.Device;
import it.epicode.DevicesManagment.datalayer.entities.enums.Status;
import it.epicode.DevicesManagment.datalayer.repositories.DeviceRepository;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository device;

    @Autowired
    EmployeeService employee;

    @Override
    public Page<Device> getAll(Pageable p) {
        return device.findAll(p);
    }

    @Override
    public Device getById(Long id) {
        return device.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Device delete(Long id) {
        var s = this.getById(id);
        device.delete(s);
        return s;
    }

    @Override
    public void assignTo(Long employeeId, Long deviceId) {
        var e = employee.getById(employeeId);
        var d = this.getById(deviceId);

        //VERIFICO SE IL DEVICE è GIA STATO ASSEGNATO
        if (d.getStatus() == Status.ASSIGNED) {
            if (Objects.equals(d.getEmployee().getId(), e.getId())){
                throw new AlreadyAssignedException("This device is already assigned to this employee");
            }
            //RECUPERO L'EMPLOYEE A CUI è STATO ASSEGANTO
           var currentEmployee = d.getEmployee();
           //ESCLUDO DALL LISTA DEI DEVICE DELL'EMPLOYEE IL DEVICE CHE VOGLIO RIASSGENARE
            currentEmployee.getDevices().remove(d);
            //AGGIORNO L'EMPLOYEE
            employee.update(currentEmployee.getId(), currentEmployee);
        }
        //AGGIUNGO ALLA LISTA IL NUOVO DEVICE
            e.getDevices().add(d);
            //AGGIORNO L'EMPLOYEE
            employee.update(e.getId(), e);
            //IMPOSTO LO STATUS DEL DEVICE SU ASSEGNATO
            d.setStatus(Status.ASSIGNED);
            //IMPOSTO L'EMPLOYEE PER IL DEVICE
            d.setEmployee(e);
            //AGGIORNO IL DEVICE
            device.save(d);

    }
}
