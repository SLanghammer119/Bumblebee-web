/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.ctrl;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Stefanie Langhammer
 */
public class LookUpData implements Serializable {
    
    protected DataBeanRemote lookUpDataBeanRemote() {

        try {
//            ResourceBundle rb=ResourceBundle.getBundle("jndi");

            Properties props = new Properties();

            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");

            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");

            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            // optional.  Defaults ist der  localhost.  
            // props.setProperty("org.omg.CORBA.ORBInitialHost", "klu.com.de");
            // props.setProperty("org.omg.CORBA.ORBInitialHost", "87.106.54.237");
            //  props.setProperty("org.omg.CORBA.ORBInitialHost", "85.214.244.179");
            //props.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.178.63");                
            // optional.  Defaults ist Port 3700.  nur benötigt wenn der Port unterschiedlich ist zu 3700.
            // props.setProperty("org.omg.CORBA.ORBInitialPort", "3080");
            //props.setProperty("Context.SECURITY_PRINCIPAL","username" );
            // props.setProperty("Context.SECURITY_CREDENTIALS","password" ); 
            Context c = new InitialContext(props);

            return (DataBeanRemote) c.lookup("ejb/dataBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
