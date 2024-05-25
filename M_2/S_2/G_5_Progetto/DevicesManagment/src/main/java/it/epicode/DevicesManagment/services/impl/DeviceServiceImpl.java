package it.epicode.DevicesManagment.services.impl;

import it.epicode.DevicesManagment.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.entities.Device;
import it.epicode.DevicesManagment.entities.Employee;
import it.epicode.DevicesManagment.entities.enums.Status;
import it.epicode.DevicesManagment.repositories.DeviceRepository;
import it.epicode.DevicesManagment.repositories.EmployeeRepository;
import it.epicode.DevicesManagment.services.interfaces.DeviceService;
import it.epicode.DevicesManagment.services.interfaces.EmployeeService;
import it.epicode.DevicesManagment.utils.EntitiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository device;

    @Autowired
    EmployeeService employee;

    @Autowired
    EntitiesUtils utils;

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

        //VERIFICO S EIL DEVICE è GIA STATO ASSEGNATO
        if (d.getStatus() != Status.AVAILABLE) {
            //RECUPERO L'EMPLOYEE A CUI è STATO ASSEGANTO
           var currentEmployee = d.getEmployee();
           //ESCLUDO DALL LISTA DEI DEVICE DELL'EMPLOYEE IL DEVICE CHE VOGLIO RIASSGENARE
           var newDeviceList = currentEmployee.getDevices()
                   .stream()
                   .filter(device-> !Objects.equals(device.getId(), deviceId))
                   .toList();
           //AGGIORNO EFFETTIVAMENTE LA LISTA DELL'EMPLOYEE
           currentEmployee.setDevices(newDeviceList);
            //AGGIORNO L'EMPLOYEE
            employee.update(currentEmployee.getId(), currentEmployee);
        }

        //RECUPERO LA LISTA DI DEVICES DELL'EMPLOYEE A CUI DEVO ASSEGANRE IL DEVICE
        var actualEmployeeDevicesList= e.getDevices();
        //AGGIUNGO ALLA LISTA IL NUOVO DEVICE SENZA TOGLIERE I DEVICES CHE C'ERANO PRIMA
        actualEmployeeDevicesList.add(d);
        //AGGIORNO EFFETTIVAMENTE LA LISTA
        e.setDevices(actualEmployeeDevicesList);
        //AGGIORNO L'EMPLOYEE
        employee.update(e.getId(), e);
        //IMPOSTO LO STATUS DEL DEVICE SU ASSEGNATO
        d.setStatus(Status.ASSIGNED);
        //AGGIRONO IL DEVICE
        device.save(d);
    }
}
