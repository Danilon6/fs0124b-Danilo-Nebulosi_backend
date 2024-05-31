package it.epicode.Events.businesslayer.services.interfaces;



public interface Mapper<D,S > {

    S map(D input);
}
