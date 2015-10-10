<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">  
     <xsl:template match="/"> 
        <html>  
        <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"/></head>
            <body>  
               
                <xsl:apply-templates select="xml"/>
            
            </body>  
        </html>  
   </xsl:template>
     
        
        <xsl:template match="xml"> 

        <xsl:if test="status = 1">
        <xsl:apply-templates select="data"/>   
        </xsl:if>
          <xsl:if test="status=0">
<xsl:value-of select="message"/>  
         </xsl:if>
        </xsl:template>  
          
        <xsl:template match="data">  
         
        <br/>  
        <xsl:value-of select="time"/>  
        <br/>
        <xsl:value-of select="context"/>  
        </xsl:template>  

        
</xsl:stylesheet>  