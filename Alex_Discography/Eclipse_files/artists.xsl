<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://alexdiscography.org"
                xmlns="http://www.w3.org/1999/xhtml">


  <!-- To enable XmlToHtmlDriver.java print out the HTML file  -->
  <xsl:output method="html"/>  

  <xsl:template match="b:artists">
    <html>
    
    
      <head>
      	<title>Alex Project</title>
      	<link rel="stylesheet" type="text/css" href="artists.css" />
      	
      	    <link href='http://fonts.googleapis.com/css?family=Spicy+Rice' rel='stylesheet' type='text/css'/>
	<link href='http://fonts.googleapis.com/css?family=Allan' rel='stylesheet' type='text/css'/>
	<link href='http://fonts.googleapis.com/css?family=Julius+Sans+One' rel='stylesheet' type='text/css'/>

      	
      	<!--Include jQuery-->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!--Include jQuery Folders script-->
	<script src="jquery.app-folders.js"></script>
	
	<script>
	$(function() {
		$('.app-folders-container').appFolders({
			opacity: .2, 								// Opacity of non-selected items
			// marginTopAdjust: true, 						// Adjust the margin-top for the folder area based on row selected?
			marginTopBase: 0, 							// If margin-top-adjust is "true", the natural margin-top for the area
			marginTopIncrement: 50,						// If margin-top-adjust is "true", the absolute value of the increment of margin-top per row
			animationSpeed: 200,						// Time (in ms) for transitions
			URLrewrite: true, 							// Use URL rewriting?
			URLbase: "/",						// If URL rewrite is enabled, the URL base of the page where used. Example (include double-quotes): "/services/"
            internalLinkSelector: ".jaf-internal a"		// a jQuery selector containing links to content within a jQuery App Folder
		});
	});
	</script>
      	
      	
      	
      	
        <title><xsl:value-of select="b:name/text()"/></title>
      </head>
      <body>
      
      <div id="iphone">
      <p id="som"> Alex Discography</p>
        <div class="iphone-positioner">
            <div class="app-folders-container">
      
      
      			<xsl:apply-templates select="b:artist"/>
      	
      		</div>
        </div>
    </div>
 
      </body>
    </html>
  </xsl:template> 
  
  <xsl:template match="b:artist">
  
  				<div class="jaf-row jaf-container">
                    <div class="folder" id="{generate-id(b:name)}">
                        <a href="#"> <xsl:apply-templates select="b:name"/> </a>
                    </div>
                </div>
                
                
                <div class="folderContent {generate-id(b:name)}">
                    <div class="jaf-container">
                    	<h3>Genre(s): <xsl:apply-templates select="b:genre"/></h3>
                    	<h2>Album(s):</h2>
                        <div>
                            <ul> 
                            	<xsl:for-each select="b:album">
								<li>
								<!-- 	<xsl:apply-templates select="."/>   -->
									<xsl:value-of select="."/>
								</li>
								</xsl:for-each>
                            
                            </ul>
                        </div>
                    </div>
                </div>
        
  </xsl:template> 

  
  
  
  

  <xsl:template match="b:name|b:genre|b:email|b:album">
    <xsl:value-of select="text()"/>
  </xsl:template>

</xsl:stylesheet>
