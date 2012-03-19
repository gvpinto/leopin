package org.springframework.security.authentication;

import org.springframework.security.core.SpringSecurityCoreVersion;

import java.io.Serializable;

/**
* A holder of the context as a string.
*
* @author Ruud Senden
* @since 2.0
*/
@Deprecated
public class AuthenticationDetails implements Serializable {

   private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

   //~ Instance fields ================================================================================================

   private final String context;

   //~ Constructors ===================================================================================================

   /**
    * Constructor.
    *
    * @param context that the authentication request is initiated from
    */
   public AuthenticationDetails(Object context) {
       this.context = context == null ? "" : context.toString();
       doPopulateAdditionalInformation(context);
   }

   //~ Methods ========================================================================================================

   /**
    * Provided so that subclasses can populate additional information.
    *
    * @param context the existing contextual information
    */
   protected void doPopulateAdditionalInformation(Object context) {}

   public boolean equals(Object obj) {
       if (obj instanceof AuthenticationDetails) {
           AuthenticationDetails rhs = (AuthenticationDetails) obj;

           // this.context cannot be null
           if (!context.equals(rhs.getContext())) {
               return false;
           }

           return true;
       }

       return false;
   }

   /**
    * Indicates the context.
    *
    * @return the context
    */
   public String getContext() {
       return context;
   }

   public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(super.toString() + ": ");
       sb.append("Context: " + this.getContext());

       return sb.toString();
   }
}
