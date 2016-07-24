package au.com.softwarekitchen.domain;

public enum AddressType implements Type {


    /**
     * This refers to the residential address of an individual (never use 'Home Address')
     * This must never be a PO Box address
     */
    RESIDENTIAL,

    /**
     * This is where to send post.
     * Applies to both individuals and organisations (never use 'Mailing Address')
     */
    POSTAL,

    /**
     * This only applies to organisations (never individuals) and refers to a physical location
     * This must never be a PO Box address
     */
    PHYSICAL,

    /**
     * An Australian company must have a registered office to which correspondence can be addressed.
     * A registered office does not need to be located at the company's place of business.
     *
     * This applies to companies
     * This is a physical address (and must never be a PO Box address)
     * but for regulatory reasons, this must never be displayed as 'Physical Address'
     */
	REGISTERED_OFFICE,

    /**
     * This is an address to which products may be shipped.
     */
    SHIPPING,

    /**
     * This is address used for support
     * This will also be used in system-generated emails
     */
    SUPPORT
}
