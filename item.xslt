<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Top Tools for Learning and Teaching 2020</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Name</th>
                        <th>Description</th>
                        <th>Link</th>
                        <th>Style</th>
                        <th>Category</th>
                        <th>Web Based?</th>
                        <th>Price</th>
                        <th>Subjects</th>
                        <th>Creator</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="tool/name"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/description"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/link"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/style"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/category"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/web_based"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/price"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/subjects"/>
                        </td>
                        <td>
                            <xsl:value-of select="tool/creator"/>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

