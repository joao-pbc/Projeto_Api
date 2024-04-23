ALTER TABLE medicos ADD COLUMN ativo BOOLEAN DEFAULT true;
UPDATE medicos SET ativo = true;