package it.epicode.DevicesManagment.businesslayer.services.interfaces;



public interface Mapper<D,S > {

    S map(D input);
}
